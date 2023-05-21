import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';

  items: MenuItem[] | undefined;
  activeItem: MenuItem | undefined;

    ngOnInit() {
        this.items = [
            { label: 'Home', icon: 'pi pi-fw pi-home', routerLink: "/home" },
            { label: 'Products', icon: 'pi pi-fw pi-box', routerLink: "/products" },
            { label: 'Edit', icon: 'pi pi-fw pi-pencil' },
            { label: 'Documentation', icon: 'pi pi-fw pi-file' },
            { label: 'Settings', icon: 'pi pi-fw pi-cog' }
        ];
        this.activeItem = this.items[0];
    }
    onActiveItemChange(event: MenuItem | undefined){
      this.activeItem = event;
  }
}
