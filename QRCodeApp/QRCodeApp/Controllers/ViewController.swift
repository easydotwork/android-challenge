//
//  ViewController.swift
//  QRCodeApp
//
//  Created by leandro de araujo estrada on 09/12/20.
//

import UIKit

class ViewController: BaseViewController {
    
    
    @IBOutlet weak var readQRButton: UIButton! {
        didSet {
            readQRButton.layer.cornerRadius = 9
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}

