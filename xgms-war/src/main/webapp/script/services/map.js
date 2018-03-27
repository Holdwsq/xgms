/**
 * Created by feiyong on 2016/6/22.
 */
app.add.factory('map', function () {
    /**
     * Map类实现
     * @param map 根据已有key/value构造
     * @constructor
     */
    function Map(map) {

        //存key/索引关系
        this.__map = {};
        this.__keys = [];
        this.__values = [];

        if (map) {
            var i = 0;

            for (var it in map) {
                this.__map[it] = i;
                this.__keys.push(it);
                this.__values.push(map[it]);
            }
        }

        this.toString = function () {
            var lst = [];
            for (var i = 0; i < this.__keys.length; i++) {
                lst.push('{' + this.__keys[i] + ':' + this.__values[i] + '}');
            }
            return lst.toString();
        };
    }

    Map.prototype = {

        /**
         * 存入值
         * @param k key
         * @param v value
         * @returns {Map}
         */
        put: function (k, v) {
            //console.log("put:" + k);
            var i = this.__map[k];
            if (i) {
                this.__values.splice(i, 1, v); //替换新value
                return this;
            }

            i = this.__keys.push(k) - 1;
            this.__map[k] = i;
            this.__values.push(v);
            return this;
        },

        /**
         * 根据key获取value
         * @param k key
         * @returns value
         */
        get: function (k) {
            var i = this.__map[k];
            return this.__values[i];
        },

        /**
         * 移除指定的key,返回对应的value,没有对应的value返回undefined
         * @param k key
         * @returns {*}
         */
        remove: function (k) {
            //先检测是否存在key
            if(this.__map[k] == undefined) return ;

            var _i, _k;
            //重建索引
            for (var i = 0, _k; i < this.__keys.length; i++) {
                _k = this.__keys[i];

                if(k == _k) {
                    _i = i;
                } else if(typeof _i != 'undefined' && i > _i) {
                    this.__map[_k] = i - 1;
                }
            }

            delete this.__map[k];
            this.__keys.splice(_i, 1);
            var v = this.__values[_i];
            this.__values.splice(_i, 1);
            return v;
        },


        /**
         * 清空map
         */
        clear: function () {
            this.__map = {};
            this.__keys = [];
            this.__values = [];
        },

        /**
         * 获取所有key
         * @returns {Array}
         */
        keys: function () {
            return this.__keys;
        },

        /**
         * 获取所有value
         * @returns {Array}
         */
        values: function () {
            return this.__values;
        },

        /**
         * 存进map
         * @param map
         * @returns {Map}
         */
        putAll: function (map) {
            if (map) {
                var i = this.__keys.length - 1;

                for (var it in map) {
                    this.__map[it] = i;
                    this.__keys.push(it);
                    this.__values.push(map[it]);
                }
            }

            return this;
        },

        /**
         * 把map转换成数组,形式:[{key:value}, {key1:value1}...]
         * @returns {Array}
         */
        toArray: function () {
            var lst = [];

            for (var i = 0; i < this.__keys.length; i++) {
                lst.push({key:this.__keys[i], value:this.__values[i]});
            }

            return lst;
        },

        /**
         * 过滤map,函数返回true时存入map
         * @param func 过滤函数
         * @returns {Map} 返回过滤后的新map
         */
        filter: function (func) {
            if (typeof func != 'function') {
                return this;
            }

            var nMap = {}, it, k;
            this.each(function (k, v) {
                if (func.call(this, k, v)) {
                    nMap[k] = v;
                }
            });

            return new Map(nMap);
        },

        /**
         * 遍历map
         * @param func 回调函数fn(key, value) this为:{key:key1, value:value1}
         * @returns {Map}
         */
        each: function (func) {
            if (typeof func != 'function') {
                return this;
            }

            var it, k;
            for (var i = 0; i < this.__keys.length; i++) {
                k = (this.__keys[i]);
                it = {};
                it[k] = this.__values[i];

                func.call({key: k, value: it[k]}, k, it[k]);
            }

            return this;
        },

        /**
         * 根据key排序
         * @param func 排序函数
         * @returns {Map}
         */
        sort: function (func) {
            if (typeof func != 'function') {
                return ;
            }

            var list = this.toArray().sort(func);
            this.clear();

            for(var i=0; i<list.length; i++) {
                this.put(list[i].key, list[i].value);
            }

            return this;
        }
    }

    return Map;
    })