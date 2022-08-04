package crude;

public class foodModel {
	  		private String Sl_no;
			private String food_item;
			private int price;
			private int serving;
			private int table_no;
			private String notes;
			public foodModel() {
				super();
			}
			public foodModel(String Sl_no,String food_item, int price,int serving,int table_no,String notes) {
				super();
				this.Sl_no=Sl_no;
				this.food_item = food_item;
				this.price = price;
				this.serving=serving;
				this.table_no=table_no;
				this.notes=notes;
				
			}
			public String getSl_no() {
				return Sl_no;
			}
			public void setSl_no(String Sl_no) {
				this.Sl_no=Sl_no;
			}

			public String getFood_item() {
				return food_item;
			}

			public void setFood_item(String food_item) {
				this.food_item = food_item;
			}

			public int getPrice() {
				return price;
			}

			public void setPrice(int price) {
				this.price = price;
			}
			public int getserving(int serving) {
				return serving;
			}
			public void setserving(int serving) {
				this.serving=serving;
			}
			public int gettableno(int table_no) {
				return table_no;
			}
			public void settableno(int table_no) {
				this.table_no=table_no;
			}
			
			public String getnotes(String notes) {
				return notes;
			}
			public void setnotes(String notes) {
				this.notes=notes;
			}

}
