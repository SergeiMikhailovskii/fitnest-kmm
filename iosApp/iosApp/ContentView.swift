import SwiftUI
import shared

struct ContentView: View {
    @State private var login: String = ""
    @State private var password: String = ""
    
    var body: some View {
        ZStack {
            Color.white.ignoresSafeArea()
            VStack(alignment: .center) {
                TextField("Login", text: $login)
                    .frame(height: 48)
                    .overlay(RoundedRectangle(cornerRadius:16).stroke(Color.gray))
                    .foregroundColor(Color.black)
                    .padding(EdgeInsets(top: 32, leading: 16, bottom: 0, trailing: 16))
                TextField("Password", text: $password)
                    .frame(height: 48)
                    .overlay(RoundedRectangle(cornerRadius:16).stroke(Color.gray))
                    .foregroundColor(Color.black)
                    .padding(EdgeInsets(top: 32, leading: 16, bottom: 0, trailing: 16))
                Spacer()
                Button("Login") {
                    LoginUseCase().run(params: LoginData(login: login, password: password), completionHandler:{_,_ in
                        
                    })
                }
            }
        }.onTapGesture {
            hideKeyboard()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

#if canImport(UIKit)
extension View {
    func hideKeyboard() {
        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
    }
}
#endif
