package myapp;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

import javax.inject.Inject;
import java.util.UUID;

class UserIdFactory {
    @Inject
    Datastore datastore;

    public UserId generate() {
        String userIdString = UUID.randomUUID().toString();
        return new UserId(userIdString);
    }
    public Key getKey(UserId userId) {
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("User");
        return keyFactory.newKey(userId.userId);
    }
}
