/**
 * @author Derek Liu
 *
 * A class that implements an ordered dictionary using a binary search tree.
 */

public class BinarySearchTree implements BinarySearchTreeADT{

    private BinaryNode root;

    public BinarySearchTree(){
        root =new BinaryNode();
    }

    @Override
    public BinaryNode getRoot(){
        return root;
    }

    //returns pixel storing given key
    @Override
    public Pixel get(BinaryNode r, Location key){
        while(!r.isLeaf()){
            Pixel data = r.getData();
            int locationnumbercompare= data.getLocation().compareTo(key);
            if(locationnumbercompare ==0)
                return data;
            else if(locationnumbercompare == -1)
                r = r.getRight();
            else
                r = r.getLeft();
        }
        return null;
    }

    //  Inserts
    //the given data in the tree if no data item with the same key is already there
    @Override
    public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException{
        BinaryNode location =root;
        if(!location.isLeaf()){
            while(!location.isLeaf()){
                int locationnumbercompare=location.getData().getLocation().compareTo(data.getLocation());
                if(locationnumbercompare==0)
                    break;
                else if(locationnumbercompare ==-1)
                    location = location.getRight();
                else
                    location = location.getLeft();
            }
        }
        r = location;
        if(!r.isLeaf())
            throw new DuplicatedKeyException();
        else{
            BinaryNode leftchild = new BinaryNode();
            BinaryNode rightchild = new BinaryNode();
            r.setParent(r.getParent());
            r.setData(data);
            rightchild.setParent(r);
            leftchild.setParent(r);
            r.setRight(rightchild);
            r.setLeft(leftchild);
        }
    }

    //Removes the data item with the given key, if the key is stored in the tree
    @Override
    public void remove(BinaryNode r, Location key) throws InexistentKeyException{
        BinaryNode location =root;
        if(!location.isLeaf()){
            while(!location.isLeaf()){
                int locationnumbercompare = location.getData().getLocation().compareTo(key);
                if(locationnumbercompare==0)
                    break;
                else if(locationnumbercompare ==-1)
                    location = location.getRight();
                else
                    location = location.getLeft();
            }
        }
        r = location;
        if(r.isLeaf())
            throw new InexistentKeyException();

        if(!r.isLeaf()){
            if(!r.getLeft().isLeaf()&&!r.getRight().isLeaf()){
                BinaryNode node=r.getRight();
                //if node to be deleted has 2 children, find inorder successor to node, which is the leftmost
                //node of the right subtree
                while(!node.isLeaf())
                    node=node.getLeft();
                BinaryNode snode=node.getParent();
                r.setData(snode.getData());
            }
            else{
                if(r.getLeft().isLeaf()){ //if node to be deleted is has a leaf child
                    BinaryNode pnode=r.getParent();
                    BinaryNode rnode =r.getRight();
                    if(pnode == null){//if root node
                        root = rnode;
                        if(!rnode.isLeaf())
                            rnode.setParent(null);
                    }
                    else{
                        if(pnode.getRight().equals(r)){
                            pnode.setRight(rnode);
                        }
                        else{
                            pnode.setLeft(rnode);
                        }
                    }
                }
                if(r.getRight().isLeaf()){
                    BinaryNode pnode=r.getParent();
                    BinaryNode lnode=r.getLeft();
                    if (pnode==null){ //if root node
                        root =lnode;
                        if(!lnode.isLeaf())
                            lnode.setParent(null);
                    }
                    else{
                        if(pnode.getRight().equals(r)){
                            pnode.setRight(lnode);
                        }
                        else{
                            pnode.setLeft(lnode);
                        }
                    }
                }
            }
        }
    }

    //Returns the Pixel with the smallest
    //key larger than the given one
    @Override
    public Pixel successor(BinaryNode r, Location key){
        if(r.isLeaf())
            return null;
        else{
            BinaryNode location=root;
            if(!location.isLeaf()){
                while(!location.isLeaf()){
                    int locationnumbercompare=location.getData().getLocation().compareTo(key);
                    if (locationnumbercompare ==0)
                        break;
                    else if (locationnumbercompare ==-1)
                        location =location.getRight();
                    else
                        location=location.getLeft();
                }
            }
            BinaryNode node=location;
            if(!node.isLeaf()&&!node.getRight().isLeaf()){
                node =node.getRight();
                while(!node.isLeaf())
                    node= node.getLeft();
                return node.getParent().getData();
            }
            BinaryNode pnode =node.getParent();
            while(pnode.getRight()==node){
                node =pnode;
                pnode=pnode.getParent();
            }
            if(pnode.getRight()!=node)
                return pnode.getData();
        }
        return null;
    }

    //Returns the Pixel with the
    //largest key smaller than the given one
    @Override
    public Pixel predecessor(BinaryNode r, Location key){
        if(r.isLeaf())
            return null;
        else{
            BinaryNode location =root;
            if(!location.isLeaf()){
                while(!location.isLeaf()){
                    int locationnumbercompare=location.getData().getLocation().compareTo(key);
                    if(locationnumbercompare ==0)
                        break;
                    else if(locationnumbercompare ==-1)
                        location =location.getRight();
                    else
                        location=location.getLeft();
                }
            }
            BinaryNode node=location;
            if(!node.isLeaf()&&!node.getLeft().isLeaf()){
                node =node.getLeft();
                while(!node.isLeaf())
                    node =node.getRight();
                return node.getParent().getData();
            }
            BinaryNode pnode=node.getParent();
            if(pnode.getLeft()==null)
                return null;
            while(pnode.getLeft()==node){
                node= pnode;
                pnode=pnode.getParent();
            }
            return pnode.getData();
        }
    }

    //Returns the Pixel
    //with the smallest key
    @Override
    public Pixel smallest(BinaryNode r) throws EmptyTreeException {
        if(r.isLeaf())
            throw new EmptyTreeException();
        else{
            BinaryNode pnode =r;
            while(!r.isLeaf()){
                pnode = r;
                r =r.getLeft();
            }
            return pnode.getData();
        }
    }


    //Returns the Pixel with
    //the largest key
    @Override
    public Pixel largest(BinaryNode r) throws EmptyTreeException {
        if(r.isLeaf())
            throw new EmptyTreeException();
        else{
            BinaryNode pnode =r;
            while(!r.isLeaf()){
                pnode= r;
                r= r.getRight();
            }
            return pnode.getData();
        }
    }
}
