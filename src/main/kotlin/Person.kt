data class Person(val firstName: String, val lastName: String) {
    fun sayMyName(): String {
        return "My name is $firstName $lastName"
    }
}