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

package de.drolpi.terminal.common.connection;

import de.natrox.common.validate.Check;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface ListenerRegistrable<T> {

    void registerListener(@NotNull UUID uniqueId, @NotNull T listener);

    default @NotNull UUID registerListener(@NotNull T listener) {
        Check.notNull(listener, "listener");
        UUID uniqueId = UUID.randomUUID();
        this.registerListener(uniqueId, listener);
        return uniqueId;
    }

    void unregisterListener(@NotNull UUID uniqueId);

}
