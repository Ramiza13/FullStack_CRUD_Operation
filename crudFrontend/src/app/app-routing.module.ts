import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewusersComponent } from './components/view-user/view-user.component';
import { AdduserComponent } from './components/add-user/add-user.component';
import { UpdateuserComponent } from './components/update-user/update-user.component';

const routes: Routes = [
  { path: '', component: ViewusersComponent},
  { path: 'add', component: AdduserComponent},
  { path: 'update/:id', component: UpdateuserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
