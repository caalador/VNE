package org.percepta.mgrankvi.vne;

import com.vaadin.shared.MouseEventDetails;
import org.percepta.mgrankvi.vne.client.MyComponentClientRpc;
import org.percepta.mgrankvi.vne.client.MyComponentServerRpc;
import org.percepta.mgrankvi.vne.client.MyComponentState;

import java.util.Arrays;

// This is the server-side UI component that provides public API 
// for MyComponent
public class MyComponent extends com.vaadin.ui.AbstractComponent {

    private int clickCount = 0;

    // To process events from the client, we implement ServerRpc
    private MyComponentServerRpc rpc = new MyComponentServerRpc() {

        // Event received from client - user clicked our widget
        public void clicked(MouseEventDetails mouseDetails) {

            // Send nag message every 5:th click with ClientRpc
            if (++clickCount % 5 == 0) {
                getRpcProxy(MyComponentClientRpc.class)
                        .alert("Ok, that's enough!");
            }

            // Update shared state. This state update is automatically
            // sent to the client.
            getState().text = "You have clicked " + clickCount + " times";
        }
    };

    public MyComponent() {

        // To receive events from the client, we register ServerRpc
        registerRpc(rpc);
    }

    public void setImagesToLoad(String... images) {
        getState().urls.addAll(Arrays.asList(images));
    }

    @Override
    public MyComponentState getState() {
        return (MyComponentState) super.getState();
    }
}
