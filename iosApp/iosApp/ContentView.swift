//
//  ContentView.swift
//  iosApp
//
//  Created by Sergei Mikhailovskii on 16.09.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import presentation

struct ContentView: View {
    private let component: RootComponent
    
    init(_ component: RootComponent) {
        self.component = component
    }
    
    @ViewBuilder var body: some View {
        ComposeView(component)
            .ignoresSafeArea(.all, edges: .vertical)
    }
}

fileprivate struct ComposeView: UIViewControllerRepresentable {
    private let component: RootComponent
    
    init(_ component: RootComponent) {
        self.component = component
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(component: component)
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
