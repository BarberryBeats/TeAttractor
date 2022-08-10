package kg.geektech.teattractor

data class User(
    var firstName: String,
    var photo: String,
    var secondName: String,
    var education: Int,
    var company: List<Company>
)

data class Company(
    var name: String,
    var position: String
)
