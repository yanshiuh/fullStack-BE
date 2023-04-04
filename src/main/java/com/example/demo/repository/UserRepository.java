package com.example.demo.repository;

import com.example.demo.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository {

    Firestore db = FirestoreClient.getFirestore();

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> apiFuture = db.collection("user").get();
        List<QueryDocumentSnapshot> documentSnapshots = apiFuture.get().getDocuments();
        List<User> userList = new ArrayList<>();
        documentSnapshots.forEach(doc -> {
            userList.add(doc.toObject(User.class));
        });
        return userList;
    }

    public String createUser(User user) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> apiFuture = db.collection("user").document(user.getDocumentId()).set(user);
        return "Successfully created user " + user.getDocumentId();
    }

    public User getUser(String documentId) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = db.collection("user").document(documentId);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot doc = apiFuture.get();
        User user;
        if (doc.exists()) {
            user = doc.toObject(User.class);
            return user;
        }
        return null;
    }

    public String updateUser(User user) {
        ApiFuture<WriteResult> apiFuture = db.collection("user").document(user.getDocumentId()).set(user);
        return "Successfully updated user " + user.getDocumentId();
    }

    public String deleteUser(String documentId) {
        ApiFuture<WriteResult> apiFuture = db.collection("user").document(documentId).delete();
        return "Successfully deleted " + documentId;
    }
}
