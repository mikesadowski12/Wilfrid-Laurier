import UIKit

class Person: Codable {
    private var photo: UIImage  // photo of self
    private var spousePhoto : UIImage // photo of spouse

    // Make photo and spousePhoto Codable
    public enum CodingKeys: String, CodingKey {
        case photo // we want to encode the photo property
        case spousePhoto // you may list many properties
    }
    
    init() {
        photo = UIImage(named:"question-mark.jpg")!;
        spousePhoto = UIImage(named:"question-mark.jpg")!;
    }
    
    init(photo: UIImage, spousePhoto: UIImage) {
        self.photo = photo;
        self.spousePhoto = spousePhoto;
    }

    // unarchive
    required init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self);
        
        let photoData = try container.decode(Data.self, forKey: .photo);
        let spousePhotoData = try container.decode(Data.self, forKey: .spousePhoto);
        
        photo = try NSKeyedUnarchiver.unarchivedObject(ofClass: UIImage.self, from: photoData) ?? UIImage(contentsOfFile: "question-mark.jpg")!;
        spousePhoto = try NSKeyedUnarchiver.unarchivedObject(ofClass: UIImage.self, from: spousePhotoData) ?? UIImage(contentsOfFile: "question-mark.jpg")!;
    }

    // archive
    func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CodingKeys.self);
        
        let photoData = try? NSKeyedArchiver.archivedData(withRootObject: photo, requiringSecureCoding: true);
        try container.encode(photoData, forKey: .photo);
        
        let spousePhotoData = try? NSKeyedArchiver.archivedData(withRootObject: spousePhoto, requiringSecureCoding: true);
        try container.encode(spousePhotoData, forKey: .spousePhoto);
    }
}

let myPerson = Person();
