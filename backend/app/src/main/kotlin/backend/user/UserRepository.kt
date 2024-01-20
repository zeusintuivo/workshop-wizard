package backend.user

import backend.domain.User
import backend.domain.Workshop
import backend.domain.WorkshopRegistration
import backend.domain.WorkshopRegistrationState

class UserRepository(
    var userMap: MutableMap<Int, User> =
        mutableMapOf(
            1 to backend.admin.user1,
            2 to backend.admin.user2,
            3 to backend.admin.user3,
        ),
    var registrationMap: MutableMap<Int, WorkshopRegistration> =

        mutableMapOf(
            1 to
                WorkshopRegistration(
                    1,
                    backend.admin.user1,
                    backend.admin.workshop1,
                    WorkshopRegistrationState.APPROVED,
                ),
            2 to
                WorkshopRegistration(
                    2,
                    backend.admin.user1,
                    backend.admin.workshop2,
                    WorkshopRegistrationState.APPROVED,
                ),
            3 to
                WorkshopRegistration(
                    3,
                    backend.admin.user2,
                    backend.admin.workshop1,
                    WorkshopRegistrationState.APPROVED,
                ),
            4 to
                WorkshopRegistration(
                    4,
                    backend.admin.user3,
                    backend.admin.workshop3,
                    WorkshopRegistrationState.APPROVED,
                ),
        ),
    var workshopMap: MutableMap<Int, Workshop> =

        mutableMapOf(
            1 to backend.admin.workshop1,
            2 to backend.admin.workshop2,
            3 to backend.admin.workshop3,
        ),
) {
    fun getWorkShopRegistrations(userId: Int): List<WorkshopRegistration> {
        return registrationMap.filter {
            it.value.user.id == userId
        }.values.toList()
    }

    fun addWorkshopRegistrations(
        userId: Int,
        workshopId: Int,
    ) {
        workshopMap.get(workshopId) ?: throw RuntimeException("Workshop does not exist")
        val maxId = registrationMap.keys.max() + 1
        registrationMap.put(
            maxId,
            WorkshopRegistration(
                maxId,
                userMap[userId]!!,
                workshopMap[workshopId]!!,
                WorkshopRegistrationState.PENDING,
            ),
        )
    }

    fun cancelWorkshopRegistration(
        uesrId: Int,
        workshopId: Int,
    ) {
        val registration =
            registrationMap.filter { it.value.user.id == uesrId && it.value.workshop.id == workshopId }
                .values.firstOrNull() ?: throw RuntimeException("Workshop registration does not exist")
        registration.state = WorkshopRegistrationState.CANCELED
    }
}
