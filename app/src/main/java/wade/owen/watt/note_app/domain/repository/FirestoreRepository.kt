package wade.owen.watt.note_app.domain.repository

interface FirestoreRepository {
    fun createUserData(
        uid: String,
    )
}