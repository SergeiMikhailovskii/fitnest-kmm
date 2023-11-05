//
//  AppDelegate.swift
//  iosApp
//
//  Created by Sergei Mikhailovskii on 20.09.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import presentation
import SwiftUI

class AppDelegate: NSObject, UIApplicationDelegate {
    let rootHolder = RootHolder()

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        NapierKt.doInitNapier()
        return true
    }
}
