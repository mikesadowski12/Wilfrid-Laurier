//
//  Holiday.swift
//  sado2560_e6
//
//  Created by Mike Sadowski on 2021-03-30.
//

import UIKit;
import Foundation;

class Holiday: Decodable {
    let name: String?;
    let date: String?;
    let observed: String?;
    let publicFlag: Bool?;
}

class HolidayItem: Decodable {
    let status: Int;
    let holidays: [Holiday];
    
//    func printHolidays() {
//        for holiday in holidays {
//            let str = "\(holiday.name ?? ""), date \(holiday.date ?? ""), observed \(holiday.observed ?? "")"
//            print(str);
//        }
//    }
}

