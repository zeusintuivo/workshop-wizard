package backend

import backend.user.UserRepository
import backend.user.WorkshopDTO

class ViewRegisteredWorkshops(private val userRepository: UserRepository) {
    fun viewRegisteredWorkshops(userId: Int): List<WorkshopDTO> {
        val workshops = userRepository.getWorkShopRegistrations(userId)
        // Legg til funksjonalitet for å vise hvilke workshops brukeren har venteliste på
        return workshops
    }
}
