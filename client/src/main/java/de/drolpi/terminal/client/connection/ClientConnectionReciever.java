/*
 * Copyright 2022 dasdrolpi & gabl22
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.drolpi.terminal.client.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientConnectionReciever extends Thread {

    private final ClientConnection clientConnection;
    private BufferedReader in;

    public ClientConnectionReciever(ClientConnection clientConnection) throws IOException {
        this.clientConnection = clientConnection;
        this.in = new BufferedReader(new InputStreamReader((clientConnection.socket().getInputStream())));
    }

    @Override
    public void run() {
        String line;
        while (!Thread.interrupted() && (line = read()) != null) {
            System.out.println("call handlers");
            clientConnection.callHandlers(line);
        }
    }

    private String read() {
        try {
            System.out.println("waiting for incoming line");
            return in.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}