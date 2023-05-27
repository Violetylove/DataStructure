package linked.list.singly;

/**
 * 英雄单链表,自己写的，很粗糙
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class SinglyHeroList {
    /*
    空头结点
     */
    private HeroNode head;

    /*
    记录有效结点数量
     */
    private int size;

    // 构造空链表
    public SinglyHeroList() {
        this.head = new HeroNode();
        this.size = size();
    }

    // 构造指定非空头结点的链表
    public SinglyHeroList(HeroNode heroNode) {
        this.head = new HeroNode();
        this.head.next = heroNode;
        this.size = size();
    }

    public int getSize() {
        return size;
    }

    /**
     * 以遍历的方式获取链表长度
     * @return 链表长度
     */
    public int size(){
        if (isEmpty()) return 0;
        HeroNode temp = head.next;
        int count = 0;
        while (temp != null){
            count ++;
            temp = temp.next;
        }
        return count;
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    /**
     * 根据no来查找
     *
     * @param no 所查找结点的no
     * @return 查找到的结点
     */
    public HeroNode search(int no) {
        // 判断空否
        if (isEmpty()) {
            System.out.println("链表为空。。。");
            return null;
        } else {
            HeroNode temp = head;
            // 查找
            while (temp.next != null) {
                if (temp.next.no == no) return temp.next; // 找到结点了。
                temp = temp.next; // 没找到，继续找
            }
        }
        // 已经找遍了结点
        System.out.println("该结点不存在。。。");
        return null;
    }

    /**
     * 尾插入，不能插入重复结点
     * @param heroNode 待插入节点
     */
    public void insert(HeroNode heroNode){
        HeroNode target = head;
        while (target.next != null){
            target = target.next;
        }
        if (target == heroNode) {
            System.out.println("不能插入重复结点！会导致链表结构异常！");
            return;
        }
        target.next = heroNode;
    }

    /**
     * 根据no的顺序插入
     *
     * @param heroNode 待插入的结点
     */
    public void insertByOrder(HeroNode heroNode) {
        // 设置temp指针来指向插入位置的前一个结点。
        HeroNode temp = head;
        // 找位置
        while (temp.next != null) {
            if (temp.next.no == heroNode.no) { // 有重复的结点了，插入失败
                System.out.printf("编号%d的英雄已经存在了！插入失败！\n", heroNode.no);
                return;
            } else if (temp.next.no > heroNode.no) { // 找到插入结点的位置了啦
                heroNode.next = temp.next;
                temp.next = heroNode;
                size++;
                return;
            } else {
                // 未找到，继续找
                temp = temp.next;
            }
        }
        // temp.next为null，表示遍历了所有结点，在尾部插入
        insert(heroNode);
    }

    public HeroNode remove(int no) {
        // 设置temp指针来指向待删结点的前一个结点。
        HeroNode front = head;
        // 找位置
        while (front.next != null) { // 如果temp不指向尾结点，继续找
            if (front.next.no == no) { // 找到待删结点了
                HeroNode target = front.next; // 保存待删结点。
                front.next = front.next.next;
                size--;
                return target;
            } else front = front.next; // 未找到，继续找
        }
        // 已经找遍了结点
        System.out.println("该结点不存在。。。");
        return null;
    }

    /**
     * 删除所有结点
     */
    public void clear() {
        if (isEmpty()) System.out.println("链表初始为空，无须清空。");
        head.next = null;
        size = 0;
    }

    /**
     * 修改指定no的结点信息(no不允许修改)
     *
     * @param no       指定结点的no
     * @param name     修改后的name
     * @param nickname 修改后的nickname
     */
    public void set(int no, String name, String nickname) {
        if (isEmpty()) {
            System.out.println("链表为空。。。");
        } else {
            HeroNode target = search(no);
            target.name = name;
            target.nickname = nickname;
        }
    }

    /**
     * 返回链表倒数第index个结点
     * @param index 指定的位置
     * @return 倒数第index个结点
     */
    public HeroNode lastIndexNode(int index) {
        if (size < index || size < 1) {
            throw new RuntimeException("index超出索引范围");
        } else if (isEmpty()) {
            throw new RuntimeException("链表为空！");
        } else {
            HeroNode target = head;
            for (int i = 0; i < size - index + 1; i++) {
                target = target.next;
            }
            return target;
        }
    }

    /**
     * 反转链表
     * @return 返回一个反转了的链表
     */
    public SinglyHeroList reverse(){
        // 为空或者只有一个结点，直接返回
        if (isEmpty() || this.size == 1) return this;
        // current指向即将摘取的结点
        HeroNode current = head.next;
        // pending指向下一个摘取的结点，引入它是为了防止链表断裂丢失
        HeroNode pending = current.next;
        // 反转后链表的置空头结点
        HeroNode reverseHead = new HeroNode();
        // 遍历原链表
        while ( current.next != null){
            // 取下的结点指向新链表的非空头结点，
            current.next = reverseHead.next;
            // 非空头结点变为current，置空头结点指向它
            reverseHead.next = current;
            // 继续摘取下一个结点，current指向下一个结点。。。。。。
            current = pending;
            pending = pending.next;
        }
        // 已经还剩最后一个结点了，pending会报空指针异常，在这里额外处理
        current.next = reverseHead.next;
        reverseHead.next = current;
        // 有一个坑，reverseHead本身是空结点，不能用它构造链表
        return new SinglyHeroList(reverseHead.next);
    }

    public void showAll() {
        // 判断空否
        if (isEmpty()) {
            System.out.println("链表为空。。。");
        } else {
            // 遍历链表
            HeroNode temp = head.next;
            while (temp != null) {
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }
}
