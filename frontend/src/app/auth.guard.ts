import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { AccountService } from './account/account.service';

export class AuthGuard implements CanActivate {
  constructor(
      private router: Router,
      private accountService: AccountService
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      const user = this.accountService.userValue;
      if (user) {
          // authorised so return true
          return true;
      }

      // not logged in so redirect to login page with the return url
      this.router.navigate(['/account/login'], { queryParams: { returnUrl: state.url }});
      return false;
  }
}