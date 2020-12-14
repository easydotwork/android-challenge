//
//  UserInfoViewController.swift
//  QRCodeApp
//
//  Created by leandro de araujo estrada on 11/12/20.
//

import UIKit
import Kingfisher

class UserInfoViewController: BaseViewController {
    
    @IBOutlet weak var userName: UILabel!
    @IBOutlet weak var userEmail: UILabel!
    @IBOutlet weak var userImageView: UIImageView! {
        didSet {
            //userImageView.layer.cornerRadius = 20
            userImageView.layer.cornerRadius = userImageView.frame.size.width / 2
            userImageView.clipsToBounds = true
            //userImageView.layer.borderColor = UIColor.blue.cgColor
            //userImageView.layer.borderWidth = 4
        }
    }
    
    var userInfoData: User?
    
    override func viewDidLoad() {
        
        super.viewDidLoad()
        self.userName.text = userInfoData?.name
        self.userEmail.text = userInfoData?.email
        self.setupImage()
        
    }
    
    @IBAction func BackButton(_ sender: UIButton) {
 
        view.window?.rootViewController?.dismiss(animated: true, completion: nil)
    }
    
    private func setupImage() {
        
        userImageView.kf.indicatorType = .activity
        userImageView.kf.setImage(with: URL(string: userInfoData!.photo)) { result in
            switch result {
            case .success(let value):
                self.userImageView.image = value.image
            case .failure:
                self.userImageView.image = UIImage(named: "userImage")
                self.userImageView.kf.cancelDownloadTask()
            
            }
        }
    }   
}


