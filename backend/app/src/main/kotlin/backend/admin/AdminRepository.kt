package backend.admin

import backend.domain.User
import backend.domain.Workshop
import backend.domain.WorkshopRegistration
import backend.domain.WorkshopRegistrationState

val user1 = User(1, "John", "Doe", "john.doe@example.com")
val user2 = User(2, "Jane", "Doe", "jane.doe@example.com")
val user3 = User(3, "John", "Smith", "john.smith@example.com")

val workshop1 = Workshop(1, "Kotlin", "John Doe")
val workshop2 = Workshop(2, "Ktor", "Jane Doe")
val workshop3 = Workshop(3, "Kotlin Multiplatform", "John Doe")

class AdminRepository {
    val userMap =
        mutableMapOf(
            1 to user1,
            2 to user2,
            3 to user3,
        )

    val registrationMap =
        mutableMapOf(
            1 to WorkshopRegistration(1, user1, workshop1, WorkshopRegistrationState.APPROVED),
            2 to WorkshopRegistration(2, user1, workshop2, WorkshopRegistrationState.APPROVED),
            3 to WorkshopRegistration(3, user2, workshop1, WorkshopRegistrationState.APPROVED),
            4 to WorkshopRegistration(4, user3, workshop3, WorkshopRegistrationState.APPROVED),
        )

    val workshopMap =
        mutableMapOf(
            1 to workshop1,
            2 to workshop2,
            3 to workshop3,
        )

    fun getWorkshops(): List<AdminWorkshopDTO> {
        return workshopMap.map { workshop ->
            val registrations =
                registrationMap.filter { it.value.workshop.id == workshop.key }
                    .map { it.value }
                    .map {
                        AdminWorkshopRegistration(
                            userMap[it.user.id]!!.firstName,
                            userMap[it.user.id]!!.lastName,
                            userMap[it.user.id]!!.email,
                            it.state,
                        )
                    }
            AdminWorkshopDTO(workshop.value.title, workshop.value.teacherName, registrations)
        }
    }

    fun getWorkshopRegistrations(workshopId: Int): AdminWorkshopDTO {
        val workshop = workshopMap.get(workshopId) ?: throw RuntimeException("Workshop does not exist")
        val registrations =
            registrationMap.filter { it.value.workshop.id == workshop.id }
                .map { it.value }
                .map {
                    AdminWorkshopRegistration(
                        userMap[it.user.id]!!.firstName,
                        userMap[it.user.id]!!.lastName,
                        userMap[it.user.id]!!.email,
                        it.state,
                    )
                }
        return AdminWorkshopDTO(workshop.title, workshop.teacherName, registrations)
    }
}
