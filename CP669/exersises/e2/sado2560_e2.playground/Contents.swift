import UIKit

let Mike = Person(fName: "Mike", lName: "Applegate", bYear: 1991)
let John = Person(fName: "John", lName: "Applegate", bYear: 1990)
let Ken = Person(fName: "Ken", lName: "Applegate", bYear: 1965)

John.setAsFather(per: Ken)
if John.hasAsFather(per: Ken) {
    print(" \(Ken.getFirstName()!)  is father of \(John.getFirstName()!)")
}

if Mike.hasAsFather(per: Ken) {
    print(" \(Ken.getFirstName()!)  is father of \(Mike.getFirstName()!)")
} else {
    print(" \(Ken.getFirstName()!)  is not father of \(Mike.getFirstName()!)")
}
