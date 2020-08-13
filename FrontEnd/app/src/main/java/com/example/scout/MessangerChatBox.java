package com.example.scout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import android.provider.Settings.Secure;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.scout.app.MySingleton;
import com.example.scout.utils.PreferenceUtils;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;



import com.example.scout.utils.PreferenceUtils;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.UUID;


public class MessangerChatBox extends AppCompatActivity
{
    private WebSocketClient webSocketClient;
    private ArrayList<ChatMessage> messagesList;
    private MessageAdapter messageAdapter;
    private String FromEmail;
    private String ToEmail = "NONE";
  //  private String server_url_insert;
    private JsonObjectRequest sr;
  //  private String ToName = "none";
    // private String ToEmail = "NONE";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messanger_chat_box);

        messagesList = new ArrayList<>();
        ListView messageList = findViewById(R.id.messages_view);
        ImageButton send = findViewById(R.id.send_button);

        messageAdapter = new MessageAdapter(getApplicationContext(), R.layout.incoming_message_layout);
        messageList.setAdapter(messageAdapter);
       // Log.d("Web socket status: ", String.valueOf(webSocketClient.isOpen()));
       // server_url_insert = "http://coms-309-vb-4.cs.iastate.edu:8080/home/users/";

        FromEmail = PreferenceUtils.getEmail(MessangerChatBox.this);

        connectWebSocket();

        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EditText messageBox = findViewById(R.id.editText);
                if(!messageBox.getText().toString().isEmpty())
                {

                    webSocketClient.send(messageBox.getText().toString());
                    sendChatMessage(messageBox.getText().toString(), false);

                }
            }
        });

    }



    private boolean sendChatMessage(String messageContent, boolean incoming)
    {
        messageAdapter.add(new ChatMessage(messageContent, incoming));

        //messageAdapter.notifyDataSetChanged();
        return true;
    }

    private void connectWebSocket()
    {
        URI uri;
        // String url = "ws://echo.websocket.org";
        String url = "ws://coms-309-vb-4.cs.iastate.edu:8080/websocket/";
        url = url + FromEmail;



        try {
            uri = new URI(url);
        } catch (URISyntaxException e){
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri)
        {
            @Override
            public void onOpen(ServerHandshake serverHandshake)
            {
                Log.i("Websocket", "Opened");

            }

            @Override
            public void onMessage(final String s)
            {
                runOnUiThread(new Runnable()
                {

                    @Override
                    public void run()
                    {

                        sendChatMessage(s, true);
                        //webSocketClient.send(s);
                        System.out.println(s);
                        Log.d("msg",s);
                    }
                });

            }

            @Override
            public void onClose(int i, String s, boolean b)
            {
               Log.d("Closing: " + i,"s" + s + b);
               webSocketClient.close();
            }

            @Override
            public void onError(Exception e)
            {
                Log.d("Error: ",e.toString());
                //webSocketClient.close();
            }
        };

        //Log.i("Websocket", "Connected");
        webSocketClient.connect();

    }

    class MessageAdapter extends ArrayAdapter<ChatMessage>
    {

        private TextView chatText;
        private Context context;
        private TextView thing;
        private String ToName;
        private String server_url_insert;
        private JsonObjectRequest sr;


        public MessageAdapter(@NonNull Context context, int resource)
        {
            super(context, resource);
        }


        @Override
        public void add(@Nullable ChatMessage object)
        {

            messagesList.add(object);
            super.add(object);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            ChatMessage obj = messagesList.get(position);
            View row = convertView;
            String content = obj.getMessage();
            boolean incoming = obj.isIncomingMessage();
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            if(incoming)
            {
                row = inflater.inflate(R.layout.incoming_message_layout, parent, false);
                Log.d("if true", "");
              //  thing = (TextView) row.findViewById(R.id.name);
               // System.out.println("ToName in thing: " + ToName);
                //thing.setText(ToName);

            }
            else
             {
                row = inflater.inflate(R.layout.my_message, parent, false);
                Log.d("if flase",row.toString());

            }

            chatText = (TextView) row.findViewById(R.id.message_body);
            chatText.setText(content);


            return row;
        }


    }


}