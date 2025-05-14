export class Review {
    userId: String;
    userName: String;
    rating: number;
    comment: String;
    date: Date;
    
    constructor(userId: string, userName: string, rating: number, comment: string, date: Date) {
        this.userId = userId;
        this.userName = userName;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
      }
}
