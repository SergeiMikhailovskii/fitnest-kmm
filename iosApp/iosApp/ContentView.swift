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
    @ViewBuilder var body: some View {
        ComposeView()
            .ignoresSafeArea(.all, edges: .bottom)
    }
}

fileprivate struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
