import DateTimeFormat = Intl.DateTimeFormat;

export interface Product {
  idProduct: number;
  codeProduct: string;
  nameProduct: string;
  initialPrice: number;
  finalPrice: number;
  incrementPrice: number;
  productDescription: string;
  startDate: string;
  endDate: string;
  remainingTime: string;
  createDay: string;
  typeProduct?: any;
  approvalStatus?: any;
  biddingStatus?: any;
  imageProductList?: any;
}
