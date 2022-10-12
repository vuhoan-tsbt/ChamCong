class ObjectsHelper {
  static mergeProperties(propertyKey, firstObject, secondObject) {
    const propertyValue = firstObject[propertyKey];
    if (
      propertyValue &&
      Object.getPrototypeOf(propertyValue) === Object.prototype
    ) {
      return ObjectsHelper.mergeObjects(
        firstObject[propertyKey],
        secondObject[propertyKey]
      );
    }
    if (secondObject === undefined || secondObject[propertyKey] === undefined) {
      return firstObject[propertyKey];
    }

    return secondObject[propertyKey];
  }

  static mergeObjects(firstObject, secondObject) {
    const finalObject = {};
    if (firstObject !== undefined) {
      Object.keys(firstObject).forEach((propertyKey) => {
        finalObject[propertyKey] = ObjectsHelper.mergeProperties(
          propertyKey,
          firstObject,
          secondObject
        );
      });
    }
    if (secondObject !== undefined) {
      Object.keys(secondObject).forEach((propertyKey) => {
        finalObject[propertyKey] = ObjectsHelper.mergeProperties(
          propertyKey,
          firstObject,
          secondObject
        );
      });
    }
    return finalObject;
  }
}

export default ObjectsHelper;
