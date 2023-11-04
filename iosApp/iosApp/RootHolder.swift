//
//  RootHolder.swift
//  iosApp
//
//  Created by Sergei Mikhailovskii on 20.09.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import presentation
import SwiftUI

class RootHolder : ObservableObject {
    let lifecycle: LifecycleRegistry
    let root: RootComponent
    
    init() {
        lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        root = DefaultRootComponent(
            context: DefaultComponentContext(lifecycle: lifecycle)
        )
        LifecycleRegistryExtKt.create(lifecycle)
    }
    
    deinit {
        LifecycleRegistryExtKt.destroy(lifecycle)
    }
}
