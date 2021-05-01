//
//  Article.swift
//  sado2560_a6
//
//  Created by Mike Sadowski on 2021-03-29.
//

import UIKit

class Article: NSObject {
    private var image: UIImage?;
    private let title: String;
    private let text: String;
    private let url: String;
    private let imageUrl: String;
    
    init?(image: UIImage?, imageUrl: String, title: String, text: String, url: String) {
        if (text.isEmpty) {
            self.text = "No description provided from API"
        } else {
            self.text = text;
        }
        
        self.image = image;
        self.imageUrl = imageUrl;
        self.title = title;
        self.url = url;
    } //init?
    
    func setImage(image: UIImage) {
        self.image = image;
    }
    
    func getImage() -> UIImage {
        return self.image!;
    }
    
    func getImageUrl() -> String {
        return self.imageUrl;
    }
    
    func getTitle() -> String {
        return self.title;
    }
    
    func getText() -> String {
        return self.text;
    }
    
    func getUrl() -> String {
        return self.url;
    }
}
