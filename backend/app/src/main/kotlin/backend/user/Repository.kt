package backend.user

import kotlinx.serialization.*
import java.lang.RuntimeException


@Serializable
class User(val id: Int, val firstName: String, val lastName: String, val email: String)

enum class RegistrationState {
    PENDING, WAITLIST, APPROVED, CANCELED,

}

@Serializable
class WorkShopRegistration(val id: Int, val userId: Int, val workshopId: Int, var state: RegistrationState = RegistrationState.PENDING)
@Serializable
class Workshop(val id: Int, val title: String, val teacherName: String)

class Repository {
    var userMap = mutableMapOf<Int, User>(
        1 to User(1, "John", "Doe", "john.doe@example.com"),
        2 to User(2, "Jane", "Doe", "jane.doe@example.com"),
        3 to User(3, "John", "Smith", "john.smith@example.com",)
    )

    var registrationMap = mutableMapOf<Int, WorkShopRegistration>(
        1 to WorkShopRegistration(1,1, 1, RegistrationState.APPROVED),
        2 to WorkShopRegistration(2, 1, 2, RegistrationState.APPROVED),
        3 to WorkShopRegistration(3, 2, 1, RegistrationState.APPROVED),
        4 to WorkShopRegistration(4,3, 3, RegistrationState.APPROVED)
    )

    var workshopMap = mutableMapOf<Int, Workshop>(
        1 to Workshop(1, "Kotlin", "John Doe"),
        2 to Workshop(2, "Ktor", "Jane Doe"),
        3 to Workshop(3, "Kotlin Multiplatform", "John Doe")
    )

    fun getWorkShopRegistrations(userId: Int) : List<Workshop> {
        val workshopIds = registrationMap.filter { it.value.userId == userId && it.value.state == RegistrationState.APPROVED }.map { it.value.workshopId }
        return workshopMap.filter { workshopIds.contains(it.key) }.values.toList()
    }

    fun addWorkshopRegistrations(userId: Int, workshopId: Int) {
        workshopMap.get(workshopId) ?: throw RuntimeException("Workshop does not exist")
        val maxId = registrationMap.keys.max() + 1
        registrationMap.put(maxId, WorkShopRegistration(maxId, userId, workshopId, RegistrationState.PENDING))
    }

    fun cancelWorkshopRegistration(uesrId: Int, workshopId: Int) {
        val registration = registrationMap.filter { it.value.userId == uesrId && it.value.workshopId == workshopId }
            .values.firstOrNull() ?: throw RuntimeException("Workshop registration does not exist")
        registration.state = RegistrationState.CANCELED
    }

}
