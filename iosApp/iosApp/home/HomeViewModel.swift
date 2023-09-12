//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Rahul Tyagi on 10/08/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension HomeScreen {
    
  @MainActor class HomeViewModel:ObservableObject{
        
      private let getMoviesUseCase = GetMoviesUseCase.init()
      
      @Published private(set) var Movies:[movie] = []
      @Published private(set) var isLoading:Bool = false
      
      private var currentPage = 1
      private(set) var loadFinish : Bool = false
      
      func loadMovie() async{
          if isLoading {
              return
          }
          
          do {
              let movies =  try await getMoviesUseCase.invoke(page :Int32(currentPage))
              isLoading = false
              loadFinish = movies.isEmpty
              currentPage += 1
              
              self.Movies = self.Movies + movies
              
          } catch {
              isLoading = false
              loadFinish = false
          }
      }
      
      
    }
    
}
