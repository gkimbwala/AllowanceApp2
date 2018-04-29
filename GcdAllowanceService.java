package myapp;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;

import javax.inject.Inject;
import java.util.List;

public class GcdAllowanceService implements AllowanceService {
    @Inject
    Datastore datastore;

    @Inject
    UserIdFactory  userIdFactory;

    @Override
    public UserId signUp(String name) {

        UserId userId = userIdFactory.generate();

        Key userIdKey = userIdFactory.getKey(userId);

        Entity user = Entity.newBuilder(userIdKey)
                .set("name", name)
                .build();

        datastore.put(user);
        return userId;
    }


    @Override
    public UserId addChild(UserId parentId, String childName) {
        Key parentIdKey = userIdFactory.getKey(parentId);
        Entity parent = datastore.get(parentIdKey);
        if (parent == null)
            throw new IllegalArgumentException("No such Parent.");

        //Todo: Create Child in Users table in Datastore
        System.out.println("Adding child "+childName + " for " +parentId);
        return new UserId("c1");
    }

    // todo: create and use choreIDFactory
    //todo: create a test program to check allowance service methods
    @Override
    public Chore addChore(UserId userId, String childName, String description, float rate) {
        return null;
    }

    @Override
    public Chore submitChore(UserId userId, ChoreId choreId) {
        return null;
    }

    @Override
    public Chore approveChore(UserId userId, ChoreId choreId) {
        return null;
    }

    @Override
    public Chore rejectChore(UserId userId, ChoreId choreId) {
        return null;
    }

    @Override
    public List<Child> listChildren(UserId userId) {
        return null;
    }

    @Override
    public List<Chore> listChores(UserId userId) {
        return null;
    }
}
