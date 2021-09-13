import SwiftUI
import shared

struct LoginView: View {
    @State private var login: String = ""
    @State private var password: String = ""
    
    @ObservedObject var viewModel: LoginViewModel = LoginViewModel()
    
    var body: some View {
        let loginResult = Binding<Bool>(
            get: {self.viewModel.loginResult == .LOGIN_SUCCESS },
            set: { _ in self.viewModel.loginResult = .LOGIN_DEFAULT }
        )
        
        ZStack {
            Color.white.ignoresSafeArea()
            VStack(alignment: .center) {
                TextFieldWithPlaceHolderColor(placeHolderText: "Login", text: $login)
                TextFieldWithPlaceHolderColor(placeHolderText: "Password", text: $password)
                Spacer()
                Button("Login") {
                    viewModel.loginUser(data: LoginData(login: login, password: password))
                }
            }
        }.onTapGesture {
            hideKeyboard()
        }.alert(isPresented: loginResult) {
            Alert(title: Text("Success"))
        }
    }
}

struct TextFieldWithPlaceHolderColor: View {
    var placeHolderText: String
    @Binding var text: String
    var editingChanged: (Bool) -> () = {_ in}
    var commit: () -> () = {}
    
    var body: some View {
        ZStack(alignment: .leading) {
            if text.isEmpty { Text(placeHolderText).foregroundColor(.gray).padding(.horizontal, 16) }
            TextField("", text: $text)
                .foregroundColor(.black)
                .padding(16)
        }.overlay(RoundedRectangle(cornerRadius:16).stroke(Color.gray))
        .padding(16)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView()
    }
}

#if canImport(UIKit)
extension View {
    func hideKeyboard() {
        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
    }
}
#endif
