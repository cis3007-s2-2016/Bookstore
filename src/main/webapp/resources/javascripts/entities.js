var entityAPI = (function(){

    var ENTITY_CONSTANTS  = {
        basePath: 'rest/entity/'
    };

    /**
     * 
     * @param {String} entityName name of the entity to access
     * @param {function} callback where to send the ajax response
     * @param {object} data all properties of entity filled out
     * POST entity/{entity}/ API
     * @returns {undefined}
     */

   var createItem = function(entityName, data, callback)
   {
       var apiURL = ENTITY_CONSTANTS.basePath + entityName;
       restUtil.postData(apiURL, callback, data);
   };

    /**
     * @param {String} entityName name of the entity to access
     * @param {function} callback where to send the ajax response
     * @param {string} entityId the primary key value of the entity
     * @param {object} data all properties of entity filled out
     * PUT entity/{entity}/{id} API
     * @returns {undefined}
     */
    var editItem = function(entityName, entityId, data, callback)
    {
        var apiURL = ENTITY_CONSTANTS.basePath + entityName + '/' + entityId;
        restUtil.putData(apiURL, callback, data);
    };
    
    /**
     * @param {String} entityName name of the entity to access
     * @param {function} callback where to send the ajax response
     * @param {string} entityId the primary key value of the entity
     * DELETE /entity/{enitity}/{id} API
     * @returns {undefined}
     */
    var deleteItem = function(entityName, entityId, callback)
    {
        var apiURL = ENTITY_CONSTANTS.basePath + entityName + '/' + entityId;
        restUtil.deleteData(apiURL, callback);
    };
    
    /**
     * @param {String} entityName name of the entity to access
     * @param {function} callback where to send the ajax response
     * @param {string} entityId the primary key value of the entity
     * GET /entity/{entity}/{id} API
     * @returns {undefined}
     */
    var findItem = function(entityName, callback, entityId)
    {    
        var apiURL = ENTITY_CONSTANTS.basePath + entityName + '/' + entityId;
        restUtil.getData(apiURL, callback);
    };
    
    /**
     * @param {String} entityName name of the entity to access
     * @param {function} callback where to send the ajax response
     * GET /entity/{entity} API
     * @returns {undefined}
     */
    var findAllItem = function(entityName, callback)
    {
        var apiURL = ENTITY_CONSTANTS.basePath + entityName;
        restUtil.getData(apiURL, callback);
    };
    
    /**
     * @param {String} entityName name of the entity to access
     * @param {function} callback where to send the ajax response
     * @param {type} from description
     * @@param {type} to 
     * GET /entity/{entity}/{from}/{to} API
     * @returns {undefined}
     */
    var findRange = function (entityName, from, to, callback)
    {
        var apiURL = ENTITY_CONSTANTS.basePath + entityName + '/' + from + '/' + to;
        restUtil.getData(apiURL, callback);
    };

    /**
     * @param {String} entityName name of the entity to access
     * @param {function} callback where to send the ajax response
     * GET /entity/{entity}/{count}
     */
    var countItem = function (entityName, callback)
    {

        var apiURL = ENTITY_CONSTANTS.basePath + entityName + '/count';
        restUtil.getData(apiURL, callback);
    };

    var restUtil = (function(){
        /**
         * If no callback is assigned then this method is called to log the contents to console
         * @param {object} jqXHR
         * @param {string} textStatus
         * @returns {undefined}
         */
        var defaultCallback = function (jqXHR, textStatus)
        {
            console.log('jq-XHR: ', jqXHR);
            console.log('text-status: ', textStatus);
        };

        var getData = function(apiURL, callback)
        {
            callback = (undefined === callback)? defaultCallback: callback;

            $.ajax({
               url: apiURL,
               method: 'GET',
               contentType: 'application/json',
               dataType: 'json'
            }).complete(callback);
        };
        
        var putData = function(apiURL, callback, data)
        {
            callback = (undefined === callback)? defaultCallback: callback;

            $.ajax({
               url: apiURL,
               method: 'PUT',
               contentType: 'application/json',
               dataType: 'json',
               data: JSON.stringify(data)
            });
        };
        
        var postData = function(apiURL, callback, data)
        {
            callback = (undefined === callback)? defaultCallback: callback;
            $.ajax({
                url: apiURL,
                method: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(data)
            }).complete(callback);
        };
        
        var deleteData = function(apiURL,callback)
        {
            callback = (undefined === callback)? defaultCallback: callback;
            $.ajax({
               url: apiURL,
               method: 'DELETE',
               contentType: 'application/json',
               dataType: 'json'
            }).complete(callback);
        };
        
        return {
            getData: getData,
            putData: putData,
            postData: postData,
            deleteData: deleteData
        };
        
    })();
    
    return {
        countItem: countItem,
        createItem: createItem,
        deleteItem: deleteItem,
        editItem : editItem,
        findAllItem: findAllItem,
        findItem: findItem,
        findRange: findRange
        /* restUtil : restUtil  <-- add if you want to access restUtil methods from outside of entityAPI fn*/
    };

})();
