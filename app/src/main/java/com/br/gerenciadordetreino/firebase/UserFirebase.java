package com.br.gerenciadordetreino.firebase;

import com.br.gerenciadordetreino.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by joaov on 11/07/2017.
 */

public class UserFirebase {
    DatabaseReference firebaseReference = FirebaseDatabase.getInstance().getReference().child("User");
    private User[] users;

    public void save(User user) {
        firebaseReference.child(user.getId()).setValue(user);
    }
}
