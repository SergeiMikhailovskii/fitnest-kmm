//
//  LoginViewModel.swift
//  iosApp
//
//  Created by Sergei Mikhailovskii on 1.08.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

extension LoginView {
    
    class LoginViewModel : ObservableObject {
        
        @Published
        var loginResult: LoginResultState? = nil
        
        func loginUser(data: LoginData) {
            LoginUseCase().run(params: data, completionHandler:{_,_ in
                self.loginResult = LoginResultState.LoginSuccess()
                print("Success")
            })
        }
        
    }
    
}
