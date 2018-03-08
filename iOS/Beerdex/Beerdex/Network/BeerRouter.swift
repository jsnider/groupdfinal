//
//  BeerRouter.swift
//  Beerdex
//
//  Created by Adam Woods on 2018-03-03.
//  Copyright © 2018 Adam Woods. All rights reserved.
//

import Foundation

public enum BeerRouter {
    
    case getAll
    
    static let baseURLString = "http://188.166.170.111:8080"
    
    var method: String {
        
        switch self {
            case .getAll: return "POST"
        }
    }
    
    public func asURLRequest() -> URLRequest {
        
        let url: URL = {
            let relativePath: String?
        
            switch self {
                case .getAll: relativePath = "stream"
            }
            
            var url = URL(string: BeerRouter.baseURLString)!
            if let path = relativePath {
                url = url.appendingPathComponent(path)
            }
            return url
        }()
        
        let parameters: [String : String]? = {
            switch self {
                case .getAll: return ["lastImageID":"0"]
            }
        }()
        
        let contentType: [String : String]? = {
            switch self {
                case .getAll: return ["content-type" : "application/json"]
            }
            
        }()
        
        var request = URLRequest(url: url)
        request.httpMethod = method
        if let contentType = contentType?["content-type"] {
            request.addValue(contentType, forHTTPHeaderField: "content-type")
        }
        
        guard let params = parameters else { return request }
        
        do {
            request.httpBody = try JSONSerialization.data(withJSONObject: params, options: .prettyPrinted)
            print(String(data: request.httpBody!, encoding: .utf8)!)
        } catch let encodeError as NSError {
            print("Encoder error: \(encodeError.localizedDescription)\n")
        }
        
        return request
    }
}
