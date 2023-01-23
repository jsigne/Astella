import { Component , OnInit } from '@angular/core';
import { Observable} from 'rxjs'

import { Service } from '../model/service.model';
import { ServiceDeliveryService } from '../service/service-delivery.service';

@Component({
  selector: 'app-service-delivery',
  templateUrl: './service-delivery.component.html',
  styleUrls: ['./service-delivery.component.css']
})
export class ServiceDeliveryComponent implements OnInit {
  constructor(private serviceDeliveryService: ServiceDeliveryService) {}

  services: Service[] = [];

  ngOnInit(): void {
      console.log("get services");
      this.serviceDeliveryService.getAllServices()
      .subscribe(services => this.services = services);
      console.log("get services end");
  }


}
