import SwiftUI
import shared

struct ContentView: View {
    @State private var login: String = ""
    @State private var password: String = ""
    
    var body: some View {
        ZStack {
            Color.white.ignoresSafeArea()
            VStack(alignment: .center) {
                TextField("Login", text: $login).border(Color(UIColor.separator))
                TextField("Password", text: $password).border(Color(UIColor.separator))
                Spacer()
                Button("Login") {
                    LoginUseCase().run(params: LoginData(login: login, password: password), completionHandler:{_,_ in
                        
                    })
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
