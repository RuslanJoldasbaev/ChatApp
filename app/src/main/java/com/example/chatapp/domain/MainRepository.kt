package com.example.chatapp.domain

<<<<<<< HEAD
import com.example.chatapp.data.local.LocalStorage
import com.example.chatapp.data.models.Group
import com.example.chatapp.data.models.ResultData
import com.example.chatapp.utils.toTime
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class MainRepository(private val fb: FirebaseFirestore, private val rd: FirebaseDatabase) {
    suspend fun getGroupChatsFlow() = flow<ResultData<List<Group>>> {
        emit(
            ResultData.Success(fb.collection("groups").get().await().documents.mapNotNull {
                Group(
                    it.id, it.data!!["name"].toString()
                )
            })
        )
    }.catch {
        emit(ResultData.Error(it))
    }

    suspend fun addGroup(name: String) = flow {
        val data = mapOf(
            "name" to name
        )
        fb.collection("groups").document().set(data)
        emit(fb.collection("groups").whereEqualTo("name", name).get().await().documents.first().id)
    }.catch {
        it.printStackTrace()
    }

    suspend fun sendMessage(message: String, groupId: String) {
        val time = System.currentTimeMillis()
        val data = mapOf(
            "message" to message,
            "user" to LocalStorage().username,
            "time" to time.toString().toTime()
        )
        rd.getReference(groupId).child(time.toString()).setValue(data)
    }

    suspend fun andGroupToRealtimeDatabase(id: String) {
        rd.getReference(id).setValue(null)
    }
=======
import com.example.chatapp.data.models.Group
import com.example.chatapp.data.models.ResultData
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.flow

class MainRepository(private val fb:FirebaseFirestore,private val rd:FirebaseDatabase){


>>>>>>> origin/main
}