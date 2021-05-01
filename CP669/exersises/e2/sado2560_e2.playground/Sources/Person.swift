import Foundation

public class Person {
    private var firstName: String?
    private var lastName: String?
    private var  birthYear : Int?
    private var father : Person? // for exercise 6
    
    var age: Int? {
        get { // return the age of the person given the birth year
            let calendar = NSCalendar.init(calendarIdentifier: NSCalendar.Identifier.gregorian)
            let currentYearInt = (calendar?.component(NSCalendar.Unit.year, from: Date()))!
            if let year = birthYear {
                return currentYearInt - year
            } else {
                return nil
            }
        } // get
        set(newAge) { // given the desired age, set the birth year
            let calendar = NSCalendar.init(calendarIdentifier: NSCalendar.Identifier.gregorian)
            let currentYearInt = (calendar?.component(NSCalendar.Unit.year, from: Date()))!
            if let year = newAge {
                    birthYear = currentYearInt - year
                }
            else {
                birthYear = currentYearInt
            }
        } // set
    } // age

    public init(){
        birthYear = 2000
    }

    public init(fName: String){
        self.firstName = fName
    }

    public init(fName: String, lName: String, bYear: Int){
        self.firstName = fName
        self.lastName = lName
        self.birthYear = bYear
    }

    public  func getFirstName() -> String? {
        return self.firstName
    }

    public func getLastName() -> String? {
        return self.lastName
    }

    public func getBirthYear() -> Int? {
        return self.birthYear
    }

    public func setLastName(lastName : String) {
        self.lastName = lastName
    }

    public func greet() {
        print("How are you?")
    }

    public func getFather() -> Person{
        return self.father!
    }

    public func setAsFather(per: Person) { // for exercise
        self.father = per
    } // isFather

    public func hasAsFather(per : Person) -> Bool { // for exercise
        if (self.father?.getFirstName() == per.getFirstName() &&
            self.father?.getLastName() == per.getLastName()) {
            return true;
        }
        
        return false;
    } // hasAsFather

} // Person

extension Person {
    func setFirsName(name : String){ // you implement this function if you think you need it

    }
}
