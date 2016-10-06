/**
 * POST entity/{entity}/ API
 * @returns {undefined}
 */
ENTITY_CONSTANTS = {
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
function createItem(entityName, data, callback)
{
    var apiURL = ENTITY_CONSTANTS.basePath + entityName;
    postData(apiURL, callback, data);
}
/**
 * @param {String} entityName name of the entity to access
 * @param {function} callback where to send the ajax response
 * @param {string} entityId the primary key value of the entity
 * @param {object} data all properties of entity filled out
 * PUT entity/{entity}/{id} API
 * @returns {undefined}
 */
function editItem(entityName, entityId, data, callback)
{
    var apiURL = ENTITY_CONSTANTS.basePath + entityName + '/' + entityId;
    putData(apiURL, callback, data);
}
/**
 * @param {String} entityName name of the entity to access
 * @param {function} callback where to send the ajax response
 * @param {string} entityId the primary key value of the entity
 * DELETE /entity/{enitity}/{id} API
 * @returns {undefined}
 */
function deleteItem(entityName, entityId, callback)
{
    var apiURL = ENTITY_CONSTANTS.basePath + entityName + '/' + entityId;
    deleteData(apiURL, callback);
}
/**
 * @param {String} entityName name of the entity to access
 * @param {function} callback where to send the ajax response
 * @param {string} entityId the primary key value of the entity
 * GET /entity/{entity}/{id} API
 * @returns {undefined}
 */
function findItem(entityName, callback, entityId)
{    
    var apiURL = ENTITY_CONSTANTS.basePath + entityName + '/' + entityId;
    getData(apiURL, callback);
}
/**
 * @param {String} entityName name of the entity to access
 * @param {function} callback where to send the ajax response
 * GET /entity/{entity} API
 * @returns {undefined}
 */
function findAllItem(entityName, callback)
{
    var apiURL = ENTITY_CONSTANTS.basePath + entityName;
    getData(apiURL, callback);
}
/**
 * @param {String} entityName name of the entity to access
 * @param {function} callback where to send the ajax response
 * @param {type} from description
 * @@param {type} to 
 * GET /entity/{entity}/{from}/{to} API
 * @returns {undefined}
 */
function findRange(entityName, from, to, callback)
{
    var apiURL = ENTITY_CONSTANTS.basePath + entityName + '/' + from + '/' + to;
    getData(apiURL, callback);
}

/**
 * @param {String} entityName name of the entity to access
 * @param {function} callback where to send the ajax response
 * GET /entity/{entity}/{count}
 */
function countItem(entityName, callback)
{
    
    var apiURL = ENTITY_CONSTANTS.basePath + entityName + '/count';
    getData(apiURL, callback);
}

/**
 * If no callback is assigned then this method is called to log the contents to console
 * @param {object} jqXHR
 * @param {string} textStatus
 * @returns {undefined}
 */
function defaultCallback(jqXHR, textStatus)
{
    console.log('jq-XHR: ', jqXHR);
    console.log('text-status: ', textStatus);
}

function getData(apiURL, callback)
{
    callback = (undefined === callback)? defaultCallback: callback;
    
    $.ajax({
       url: apiURL,
       method: 'GET',
       contentType: 'application/json',
       dataType: 'json'
    }).complete(callback);
}
function putData(apiURL, callback, data)
{
    callback = (undefined === callback)? defaultCallback: callback;
    
    $.ajax({
       url: apiURL,
       method: 'GET',
       contentType: 'application/json',
       dataType: 'json',
       data: JSON.stringify(data)
    });
}
function postData(apiURL, callback, data)
{
    callback = (undefined === callback)? defaultCallback: callback;
    $.ajax({
        url: apiURL,
        method: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(data)
    }).complete(callback);
}
function deleteData(apiURL,callback)
{
    $.ajax({
       url: apiURL,
       method: 'DELETE',
       contentType: 'application/json',
       dataType: 'json'
    }).complete(callback);
}

