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
    @IBOutlet weak var userImageView: UIImageView!
    
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


