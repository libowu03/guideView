package com.kit.superTab

class SuperTab {
    private var text:String?=null
    private var iconSelectNet:String?=null
    private var iconUnselectedNet:String?=null
    private var iconUnselectedLocal:Int = 0
    private var iconSelectLocal:Int = 0
    private var position:Int = 0
    private var pointText:String?=null
    private var pointIcon:Int = 0

    open fun setText(text:String):SuperTab{
        this.text = text
        return this
    }
    open fun setIconSelectNet(iconSelectNet:String):SuperTab{
        this.iconSelectNet = iconSelectNet
        return this
    }
    open fun setIconUnselectedNet(iconUnselectedNet:String):SuperTab{
        this.iconUnselectedNet = iconUnselectedNet
        return this
    }
    open fun setIconSelectLocal(iconSelectLocal:Int):SuperTab{
        this.iconSelectLocal = iconSelectLocal
        return this
    }
    open fun setIconUnselectedLocal(text:Int):SuperTab{
        this.iconUnselectedLocal = iconUnselectedLocal
        return this
    }

    open fun getText():String{
        return text!!
    }

    open fun getIconSelectNet():String{
        return iconSelectNet!!
    }

    open fun getIconUnselectedNet():String{
        return iconUnselectedNet!!
    }

    open fun getIconUnselectedLocal():Int{
        return iconUnselectedLocal!!
    }

    open fun getIconSelectLocal():Int{
        return iconSelectLocal!!
    }

    open fun getPosition():Int{
        return position
    }

    open fun setPosition(position:Int){
        this.position = position
    }

}