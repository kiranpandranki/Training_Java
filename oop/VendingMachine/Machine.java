public class Machine {
    private Item[][] items;

    public Machine(Item[][] items){
        this.items = new Item[items.length][items[0].length];
        for(int i=0;i<items.length;i++){
            for(int j=0;j<items[i].length;j++){
                this.items[i][j] = new Item(items[i][j]);
            }
        }
    }

    public Item getItem(int row,int col){
        return new Item(this.items[row][col]);
    }

    public void setItem(Item item,int row,int col){
        this.items[row][col] = new Item(item);
    }

    public int getLength(){
        return this.items.length;
    }

    public int getRowLength(){
        return this.items[0].length;
    }

    public String toString(){
        String temp = "\n";
        for(int i=0;i<this.items.length;i++){
            temp += "\t";
            for(int j=0;j<this.items[i].length;j++){
                temp += items[i][j].toString() + "\t";
            }
            temp += "\n\n";
        }
        temp += "\n\t****************************************************\n";
        return temp;
    }

    public boolean dispense(int row,int col){
        if(this.items[row][col].getQuantity() > 0){
            this.items[row][col].setQuantity(this.items[row][col].getQuantity()-1);
            return true;
        }

        return false;
    }
}
