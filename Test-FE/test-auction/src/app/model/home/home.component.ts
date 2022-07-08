import {Component, OnInit} from '@angular/core';
import {HomeServiceService} from '../../home-service.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Product} from '../product';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import DateTimeFormat = Intl.DateTimeFormat;


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  // priceRage = [
  //   {min: 0, max: 5, name: '0-$5'},
  //   {min: 5, max: 25, name: '$5-$25'},
  //   {min: 50, max: 250, name: '$25-$250'},
  // ];
  products: Product[];
  min: number;
  max: number;
  typeProductName: string;
  formSearch: FormGroup;
  flagCheck = false;
  nameProductSearch: string;
  // tslint:disable-next-line:ban-types
  messageAlert: String[];
  remainingTime: DateTimeFormat;


  constructor(private homeService: HomeServiceService, private activatedRoute: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.showListProductAuction();
    this.formSearch = new FormGroup({
      nameProduct: new FormControl('', [Validators.required, Validators.pattern('^[^!@#$%^&*]+$')]),
      typeProductname: new FormControl(''),
      priceRange: new FormControl(''),
    });
  }

  showListProductAuction() {
    this.homeService.showListProductAuction().subscribe(
      (data) => {
        this.products = data;
        for (let i=0; i< this.products.length; i++) {
          console.log(this.products[i].remainingTime );
        }
        if (this.nameProductSearch != null) {
          this.nameProductSearch = '';
        }
        // console.log(data);
      },
      () => {
        console.log('Error product list');
      },
      () => {
      }
    );
  }

  showListProductFinished() {
    this.homeService.showListProductFinished().subscribe(
      (data) => {
        this.products = data;
        if (this.nameProductSearch != null) {
          this.nameProductSearch = '';
        }
      },
      () => {
        console.log('Error list finished product');
      },
      () => {
      }
    );
  }

  sortListProductByTypeProductName(typeProductName: string) {
    this.homeService.sortListProductByTypeProduct(typeProductName).subscribe(
      (data) => {
        this.products = data;
        if (this.nameProductSearch != null) {
          this.nameProductSearch = '';
        }
        // console.log(data);
      }
    );
  }

  searchForm() {
    const nameProduct = this.formSearch.value.nameProduct;
    const typeProductName = this.formSearch.value.typeProductname;
    const priceRange = this.formSearch.value.priceRange;
    console.log(nameProduct);
    console.log(typeProductName);
    console.log(priceRange);
    this.messageAlert = [];
    if (this.formSearch?.invalid) {
      if (this.formSearch.get('nameProduct').errors.required) {
        this.messageAlert.push('The product name to be searched cannot be empty');
        this.showListProductAuction();
      }
      if (this.formSearch.get('nameProduct').errors.pattern) {
        this.messageAlert.push('The product name cannot contain special characters(!@#$%^&*)');
        this.showListProductAuction();
      }
    }else {
      // @ts-ignore
      $('#myModal').modal('hide');
    }
    if (priceRange === '$0 – $5') {
      this.min = 0;
      this.max = 5;
      this.flagCheck = true;
    }
    if (priceRange === '$5 – $25') {
      this.min = 5;
      this.max = 25;
      this.flagCheck = true;
    }
    if (priceRange === '$25 – $50') {
      this.min = 25;
      this.max = 50;
      this.flagCheck = true;
    }
    if (priceRange === '$50 - $250') {
      this.min = 50;
      this.max = 250;
      this.flagCheck = true;
    }
    if (priceRange === '') {
      this.min = 0;
      this.flagCheck = false;
    }
    if (priceRange === 'Over $250') {
      this.min = 250;
      this.flagCheck = false;
    }
    if (this.flagCheck) {
      this.homeService.searchListProduct(nameProduct, typeProductName, this.min, this.max).subscribe(
        (data) => {
          this.products = data;
          console.log(data);
        }
      );
    }else {
      this.homeService.searchListProductByPriceOver250(nameProduct, typeProductName, this.min).subscribe(
        (data) => {
          this.products = data;
          console.log(data);
        }
      );
    }
  }

}
