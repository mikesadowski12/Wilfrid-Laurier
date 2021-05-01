//
//  utils.swift
//  sado2560_a3
//
//  Created by Mike Sadowski on 2021-02-09.
//

import Foundation

func getCurrentDate() -> String {
    let currentDateTime = Date();
    let formatter = DateFormatter();
    
    formatter.timeStyle = .medium;
    formatter.dateStyle = .long;
    
    return formatter.string(from: currentDateTime);
}
