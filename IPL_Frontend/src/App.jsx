import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { TeamPage } from './pages/TeamPage';
import { MatchPage } from './pages/MatchPage';
import { HomePage } from './pages/HomePage';
import './App.scss';

function App() {
  return (
    <div className='App'>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage/>}/>
          <Route path='/team/:teamName' element={<TeamPage/>}/>
          <Route path='/team/:teamName/matches/:year' element={<MatchPage/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App
