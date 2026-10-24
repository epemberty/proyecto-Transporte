import { HttpInterceptorFn } from '@angular/common/http';

export const devtunnelInterceptor: HttpInterceptorFn = (req, next) => {
  const authReq = req.clone({
    setHeaders: {
      'ngrok-skip-browser-warning': 'true',
      'bypass-tunnel-reminder': 'true'
    }
  });
  return next(authReq);
};