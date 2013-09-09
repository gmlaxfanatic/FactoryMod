import yaml

config_file=open('../config.yml','r')
config=yaml.load(config_file)
config_file.close()
#Sturucte Factories
categories=[('Enchanting',['Cauldron']),('Smelting',['Smelter']),('Food',['Bakery','Grill']),('Equipment',['Smithy']),('Items',['Wool','Rail'])]
sorted_factories={}
#Place factories in their respective categories
for category,keywords in categories:
    sorted_factory[category]=[]
    for factory in config['production_factories']:
        if sum([(keywords in config['production_factories'][factory]['name']) for keyword in keywords])>0:
            sorted_factory[category].append(factory)
#Place factories absent from all categories into Miscellaneous
miscellaneous=[]
for factory in config['production_factories']:
    if sum([factory in factories for factories in sorted_factories.values()]==0)
        miscellaneous.append(factory)
if miscellaneous.size()!=0:
    sorted_factory['Miscellaneous']=miscellaneous
#Sort factories alphabetically
for category,factories in sorted_factories:
    sorted_factories[category]=factories.sort()
#Write to file
production_doc=open('production_documentation.md','w')
for category,factories in sorted_factories:
    myfile.write('\n\n###'+category)
    for factory in factories:
        info=config['production_factories'][factory]
        production_doc.write('\n\n**'+info[name]+'**')
        production_doc.write(' - ')
        for input,input_info in info['input']:
                        # myfile.write(str(input.amount)+' '+input.name)
                        # if(factory.inputs.index(input)!=len(factory.inputs)-1):
                                # myfile.write(', ')
production_doc.write(yaml.dump(config,default_flow_style=False))
production_doc.close()


        # sortedFactoryKeys=config['factories'].keys()
        # sortedFactoryKeys.sort()
        # types=[('Enchanting',['Cauldron']),('Smelting',['Smelter']),('Food',['Bakery','Grill']),('Equipment',['Smithy']),('Items',['Wool','Rail'])]
        # for type,names in types:
            # myfile.write('\n\n-  ['+type+'](https://github.com/gmlaxfanatic/FactoryMod/wiki#'+type.lower()+')')        
        # for type,names in types:
            # myfile.write('\n\n###'+type)
            # for key in sortedFactoryKeys:
                # factory=config['factories'][key]
                # if sum([(name in factory.identifier) for name in names])>0:
                    # myfile.write('\n\n**'+factory.name+'**')
                    # myfile.write(' - ')
                    # for input in factory.inputs:
                        # myfile.write(str(input.amount)+' '+input.name)
                        # if(factory.inputs.index(input)!=len(factory.inputs)-1):
                                # myfile.write(', ')
                    # for recipe in factory.outputRecipes:
                        # myfile.write('\n\n\t')
                        # for output in recipe.outputs:
                            # myfile.write(str(output.amount)+' '+output.name)
                        # myfile.write(' for ')
                        # for input in recipe.inputs:
                            # myfile.write(str(input.amount)+' '+input.name)
                            # if(recipe.inputs.index(input)!=len(recipe.inputs)-1):
                                # myfile.write(', ')
                        # myfile.write(' using '+str(int(math.ceil(recipe.time/float(factory.fuelTime))))+' '+factory.fuel.name)