import Person

class PersonCommand(person: Person) {
    private var firstName: String = person.firstName
    private var lastName: String = person.lastName

    fun sayMyName(): String {
        return "My name is $firstName $lastName"
    }
}
